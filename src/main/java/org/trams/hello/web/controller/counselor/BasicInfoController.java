package org.trams.hello.web.controller.counselor;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.trams.hello.bean.Bank;
import org.trams.hello.bean.User;
import org.trams.hello.bean.UserItem;
import org.trams.hello.bean.jpa.CategoryEntity;
import org.trams.hello.bean.jpa.CounselingSessionEntity;
import org.trams.hello.bean.jpa.CounselorBiographyEntity;
import org.trams.hello.bean.jpa.CounselorEntity;
import org.trams.hello.bean.jpa.CounselorFieldEntity;
import org.trams.hello.bean.jpa.PasswordHistoryEntity;
import org.trams.hello.bean.jpa.PaymentProfitEntity;
import org.trams.hello.bean.jpa.UserCertificateEntity;
import org.trams.hello.bean.jpa.UserEducationEntity;
import org.trams.hello.bean.jpa.UserEntity;
import org.trams.hello.bean.jpa.UserFileEntity;
import org.trams.hello.business.service.BankService;
import org.trams.hello.business.service.CategoryService;
import org.trams.hello.business.service.CounselingCenterService;
import org.trams.hello.business.service.CounselingSessionService;
import org.trams.hello.business.service.CounselorBiographyService;
import org.trams.hello.business.service.CounselorFieldService;
import org.trams.hello.business.service.CounselorService;
import org.trams.hello.business.service.PasswordHistoryService;
import org.trams.hello.business.service.PaymentProfitService;
import org.trams.hello.business.service.UserCertificateService;
import org.trams.hello.business.service.UserEducationService;
import org.trams.hello.business.service.UserFileService;
import org.trams.hello.business.service.UserService;
import org.trams.hello.web.common.ApplicationDefine;
import org.trams.hello.web.common.Login;
import org.trams.hello.web.common.utils.EncryptionUtils;
import org.trams.hello.web.dto.UserDto;

/**
 * Created by bryanlee on 4/13/17.
 */
@Controller
@RequestMapping("/auth")
public class BasicInfoController extends BaseController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    private UserService userService;
    @Autowired
    private CounselorService counselorService;
    @Autowired
    private BankService bankService;
    @Autowired
    private CounselingCenterService counselingCenterService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private CounselorFieldService counselorFieldService;
    @Autowired
    private UserFileService userFileService;
    @Autowired
    private UserCertificateService userCertificateService;
    @Autowired
    private UserEducationService userEducationService;
    @Autowired
    private CounselorBiographyService counselorBiographyService;
    @Autowired
    private PasswordHistoryService passwordHistoryService;
    @Autowired
    private CounselingSessionService counselingSessionService;
    @Autowired
    private PaymentProfitService paymentProfitService;

    @RequestMapping(value = "/info/detail", method = RequestMethod.GET)
    public String info(
            HttpSession session,
            ModelMap map
    ) {
        UserItem                    userItem        = Login.getLoggedInUser(session);
        UserEntity                  user            = userService.findOne(userItem.getId());
        CounselorEntity             counselor       = counselorService.findByOne(userItem.getId());
        List<Bank>                  banks           = bankService.findAll();
        List<CategoryEntity>        categories      = categoryService.findByTypeAndIsShow((short) 1, (short) 1);
        List<UserFileEntity>        userFiles       = userFileService.listPagingByTypeAndUserId((short) 1, userItem.getId());
        List<UserCertificateEntity> certificates    = userCertificateService.findByUserId(userItem.getId());
        UserEducationEntity         education       = userEducationService.findByUserId(userItem.getId());
        List<CounselorBiographyEntity> biographies  = counselorBiographyService.findByCounselorId(userItem.getId());

        List<CounselorFieldEntity>  fields          = counselorFieldService.listBy_CounselorId(userItem.getId());
        List<Integer>               _fields         = new LinkedList<>();
        for (CounselorFieldEntity cfe : fields) {
            _fields.add(cfe.getCounselorFieldId());
        }

        Map<String, Object> notYetSessionParams     = new HashMap<>();
        notYetSessionParams.put("page", 1);
        notYetSessionParams.put("size", 10);
        notYetSessionParams.put("counselorId", userItem.getId());
        notYetSessionParams.put("statuses", new Short[] {(short) 1});

        Page<CounselingSessionEntity> notYetSesions = counselingSessionService.filter(notYetSessionParams);

        Map<String, Object> notYetPaidParams        = new HashMap<>();
        notYetPaidParams.put("page", 1);
        notYetPaidParams.put("size", 10);
        notYetPaidParams.put("userId"   , userItem.getId());
        notYetPaidParams.put("type"     , ApplicationDefine.PaymentProfitStatus.PENDING.getCode());
        Page<PaymentProfitEntity> notYetPaids       = paymentProfitService.filter(notYetPaidParams);

        map.put("categories"    , categories);
        map.put("user"          , user);
        map.put("counselor"     , counselor);
        map.put("banks"         , banks);
        map.put("fields"        , _fields);
        map.put("userFiles"     , userFiles);
        map.put("certificates"  , certificates);
        map.put("education"     , education);
        map.put("biographies"   , biographies);
        map.put("notYetSession" , notYetSesions.getContent().isEmpty());
        map.put("notYetPaid"    , notYetPaids.getContent().isEmpty());
        return getView();
    }

    @RequestMapping(value = "/info/checkpwd", method = RequestMethod.GET)
    @ResponseBody
    public Object checkPwd(
            @RequestParam String pwd,
            HttpSession session
    ) {
        UserItem            userItem    = Login.getLoggedInUser(session);
        UserEntity          user        = userService.findOne(userItem.getId());

        if (EncryptionUtils.jwtBuild(pwd).equals(user.getPassword())) {
            return SUCCESS();
        }

        return FAIL("Password did not match");
    }

    @RequestMapping(value = "/info/submit/tab1", method = RequestMethod.POST)
    @ResponseBody
    public Object submitTab1(
            @ModelAttribute UserDto param,
            HttpSession session
    ) {
        UserItem            userItem    = Login.getLoggedInUser(session);
        param.setCounselorId(userItem.getId());

        List<PasswordHistoryEntity> lastestPasswords = passwordHistoryService.findLastestByUser(userItem.getId(), 2);
        if (lastestPasswords != null && !lastestPasswords.isEmpty()) {
            for (PasswordHistoryEntity phe : lastestPasswords) {
                if (EncryptionUtils.jwtBuild(param.getPwd()).equals(phe.getPassword())) {
                    return FAIL(1);
                }
            }
        }

        userService.updateBasicInfo(param);
        return SUCCESS();
    }

    @RequestMapping(value = "/info/submit/tab2", method = RequestMethod.POST)
    @ResponseBody
    public Object submitTab2(
            @ModelAttribute UserDto param,
            HttpSession session
    ) {
        param.setCounselorId(Login.getLoggedInUser(session).getId());
        userService.updateServiceInfo(param, servletContext);
        return SUCCESS();
    }

    @RequestMapping(value = "/info/submit/tab3", method = RequestMethod.PUT)
    @ResponseBody
    public Object submitTab3(
            @RequestParam Map<String, Object> params,
            HttpSession session
    ) {
        UserItem            userItem    = Login.getLoggedInUser(session);
        User                user        = userService.findById(userItem.getId());

        user.setStatusActive((short) 3);
        user.setWithdrawalDate(new Date());
        user.setWithdrawalReason(String.valueOf(params.get("reason")));
        user.setTypeWithdrawalReason(Short.parseShort(params.get("type").toString()));

        userService.update(user);

        Login.removeLoggedInUser(session);
        return SUCCESS();
    }

}
