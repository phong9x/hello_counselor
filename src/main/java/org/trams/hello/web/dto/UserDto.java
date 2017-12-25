package org.trams.hello.web.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

/**
 * Created by bryanlee on 4/18/17.
 */
public class UserDto {

    private Integer                 counselorId;
    private Integer                 counselType;
    private Integer                 counselCenter;
    private String                  email;
    private String                  pwd;
    private String                  fullname;
    private String                  mobilephone;
    private String                  dobDay;
    private String                  dobMonth;
    private String                  dobYear;

    private String                  introduce;
    private String                  counseledTimes;

    private Integer                 mbti;
    private String                  mbtiSchool;
    private Integer                 bankId;
    private String                  address;
    private String                  accountBankHolder;
    private String                  accountBankNumber;

    private MultipartFile           avatar;
    private String                  university;
    private String                  faculty;
    private Short                   eduLevel;
    private MultipartFile           eduFile;
    private List<Field>             counselorFields     = new ArrayList<>();
    private List<Biography>         biographies         = new ArrayList<>();
    private List<Certificate>       certificates        = new ArrayList<>();
    private List<OtherCertificate>  otherCertificates   = new ArrayList<>();

    public Integer getCounselorId() {
        return counselorId;
    }

    public void setCounselorId(Integer counselorId) {
        this.counselorId = counselorId;
    }

    public Integer getCounselType() {
        return counselType;
    }

    public void setCounselType(Integer counselType) {
        this.counselType = counselType;
    }

    public Integer getCounselCenter() {
        return counselCenter;
    }

    public void setCounselCenter(Integer counselCenter) {
        this.counselCenter = counselCenter;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getMobilephone() {
        return mobilephone;
    }

    public void setMobilephone(String mobilephone) {
        this.mobilephone = mobilephone;
    }

    public String getDobDay() {
        return dobDay;
    }

    public void setDobDay(String dobDay) {
        this.dobDay = dobDay;
    }

    public String getDobMonth() {
        return dobMonth;
    }

    public void setDobMonth(String dobMonth) {
        this.dobMonth = dobMonth;
    }

    public String getDobYear() {
        return dobYear;
    }

    public void setDobYear(String dobYear) {
        this.dobYear = dobYear;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getCounseledTimes() {
        return counseledTimes;
    }

    public void setCounseledTimes(String counseledTimes) {
        this.counseledTimes = counseledTimes;
    }

    public Integer getMbti() {
        return mbti;
    }

    public void setMbti(Integer mbti) {
        this.mbti = mbti;
    }

    public String getMbtiSchool() {
        return mbtiSchool;
    }

    public void setMbtiSchool(String mbtiSchool) {
        this.mbtiSchool = mbtiSchool;
    }

    public Integer getBankId() {
        return bankId;
    }

    public void setBankId(Integer bankId) {
        this.bankId = bankId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAccountBankHolder() {
        return accountBankHolder;
    }

    public void setAccountBankHolder(String accountBankHolder) {
        this.accountBankHolder = accountBankHolder;
    }

    public String getAccountBankNumber() {
        return accountBankNumber;
    }

    public void setAccountBankNumber(String accountBankNumber) {
        this.accountBankNumber = accountBankNumber;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public Short getEduLevel() {
        return eduLevel;
    }

    public void setEduLevel(Short eduLevel) {
        this.eduLevel = eduLevel;
    }

    public MultipartFile getEduFile() {
        return eduFile;
    }

    public void setEduFile(MultipartFile eduFile) {
        this.eduFile = eduFile;
    }

    public List<Field> getCounselorFields() {
        return counselorFields;
    }

    public void setCounselorFields(List<Field> counselorFields) {
        this.counselorFields = counselorFields;
    }

    public List<Biography> getBiographies() {
        return biographies;
    }

    public void setBiographies(List<Biography> biographies) {
        this.biographies = biographies;
    }

    public List<Certificate> getCertificates() {
        return certificates;
    }

    public void setCertificates(List<Certificate> certificates) {
        this.certificates = certificates;
    }

    public List<OtherCertificate> getOtherCertificates() {
        return otherCertificates;
    }

    public void setOtherCertificates(List<OtherCertificate> otherCertificates) {
        this.otherCertificates = otherCertificates;
    }

}
