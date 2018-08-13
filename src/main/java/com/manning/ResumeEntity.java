package com.manning;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "resume")
public class ResumeEntity implements Serializable {
    /**
     * 流水号,自增
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 是否被索引到搜索库
     */
    private Integer indexed;

    /**
     * 用户ID
     */
    @Column(name = "`uid`")
    private Long uid;

    /**
     * 简历状态
     */
    @Column(name = "resume_state")
    private Integer resumeState;

    /**
     * 所在城市
     */
    private Integer city;

    /**
     * 姓名
     */
    @Column(name = "`name`")
    private String name;

    /**
     * 性别 0女1男
     */
    private Integer sex;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 是否学生 0否1是
     */
    private Integer student;

    /**
     * 出生年
     */
    @Column(name = "birth_year")
    private Integer birthYear;

    /**
     * 出生月
     */
    @Column(name = "birth_month")
    private Integer birthMonth;

    /**
     * 头像地址
     */
    @Column(name = "head_img")
    private String headImg;

    /**
     * 学历
     */
    private Integer education;

    /**
     * 工作年限
     */
    @Column(name = "working_year")
    private Integer workingYear;

    /**
     * 工作技能
     */
    private String skill;

    /**
     * 工作经历
     */
    private String experience;

    /**
     * 身高
     */
    private Integer height;

    /**
     * 籍贯
     */
    @Column(name = "native_place")
    private Integer nativePlace;

    /**
     * 邮箱
     */
    private String email;

    /**
     * QQ
     */
    private String qq;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 专业名称
     */
    private String major;

    /**
     * 求职意向,允许保存多值
     */
    @Column(name = "job_intention")
    private String jobIntention;

    /**
     * 意向工作地点,允许保存多值
     */
    @Column(name = "district_intention")
    private String districtIntention;

    /**
     * 自我描述
     */
    private String description;

    /**
     * 完善度
     */
    @Column(name = "`degree`")
    private Integer degree;

    /**
     * 平台 1 公众号，2 android,3,ios,4,pc 5 pad
     */
    private Integer platform;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

    /**
     * 来源id
     */
    @Column(name = "source_id")
    private Long sourceId;

    /**
     * 百度纬度
     */
    @Column(name = "baidu_lat")
    private Double baiduLat;

    /**
     * 百度经度
     */
    @Column(name = "baidu_lon")
    private Double baiduLon;

    /**
     * 同步到lbs时间
     */
    @Column(name = "lbs_sync_time")
    private Date lbsSyncTime;

    /**
     * 地图展示 1是 2否
     */
    @Column(name = "lbs_show")
    private Integer lbsShow;

    /**
     * 来源type
     */
    @Column(name = "source_type")
    private Integer sourceType;

    /**
     * 是否已经有对应的 oppuser 0否 1是
     */
    @Column(name = "turn_oppuser")
    private Integer turnOppuser;

    private static final long serialVersionUID = 1L;

    /**
     * 获取流水号,自增
     *
     * @return id - 流水号,自增
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置流水号,自增
     *
     * @param id 流水号,自增
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取是否被索引到搜索库
     *
     * @return indexed - 是否被索引到搜索库
     */
    public Integer getIndexed() {
        return indexed;
    }

    /**
     * 设置是否被索引到搜索库
     *
     * @param indexed 是否被索引到搜索库
     */
    public void setIndexed(Integer indexed) {
        this.indexed = indexed;
    }

    /**
     * 获取用户ID
     *
     * @return uid - 用户ID
     */
    public Long getUid() {
        return uid;
    }

    /**
     * 设置用户ID
     *
     * @param uid 用户ID
     */
    public void setUid(Long uid) {
        this.uid = uid;
    }

    /**
     * 获取简历状态
     *
     * @return resume_state - 简历状态
     */
    public Integer getResumeState() {
        return resumeState;
    }

    /**
     * 设置简历状态
     *
     * @param resumeState 简历状态
     */
    public void setResumeState(Integer resumeState) {
        this.resumeState = resumeState;
    }

    /**
     * 获取所在城市
     *
     * @return city - 所在城市
     */
    public Integer getCity() {
        return city;
    }

    /**
     * 设置所在城市
     *
     * @param city 所在城市
     */
    public void setCity(Integer city) {
        this.city = city;
    }

    /**
     * 获取姓名
     *
     * @return name - 姓名
     */
    public String getName() {
        return name;
    }

    /**
     * 设置姓名
     *
     * @param name 姓名
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取性别 0女1男
     *
     * @return sex - 性别 0女1男
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 设置性别 0女1男
     *
     * @param sex 性别 0女1男
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 获取手机号
     *
     * @return mobile - 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置手机号
     *
     * @param mobile 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 获取是否学生 0否1是
     *
     * @return student - 是否学生 0否1是
     */
    public Integer getStudent() {
        return student;
    }

    /**
     * 设置是否学生 0否1是
     *
     * @param student 是否学生 0否1是
     */
    public void setStudent(Integer student) {
        this.student = student;
    }

    /**
     * 获取出生年
     *
     * @return birth_year - 出生年
     */
    public Integer getBirthYear() {
        return birthYear;
    }

    /**
     * 设置出生年
     *
     * @param birthYear 出生年
     */
    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    /**
     * 获取出生月
     *
     * @return birth_month - 出生月
     */
    public Integer getBirthMonth() {
        return birthMonth;
    }

    /**
     * 设置出生月
     *
     * @param birthMonth 出生月
     */
    public void setBirthMonth(Integer birthMonth) {
        this.birthMonth = birthMonth;
    }

    /**
     * 获取头像地址
     *
     * @return head_img - 头像地址
     */
    public String getHeadImg() {
        return headImg;
    }

    /**
     * 设置头像地址
     *
     * @param headImg 头像地址
     */
    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public Integer getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(Integer education) {
        this.education = education;
    }

    /**
     * 获取工作年限
     *
     * @return working_year - 工作年限
     */
    public Integer getWorkingYear() {
        return workingYear;
    }

    /**
     * 设置工作年限
     *
     * @param workingYear 工作年限
     */
    public void setWorkingYear(Integer workingYear) {
        this.workingYear = workingYear;
    }

    /**
     * 获取工作技能
     *
     * @return skill - 工作技能
     */
    public String getSkill() {
        return skill;
    }

    /**
     * 设置工作技能
     *
     * @param skill 工作技能
     */
    public void setSkill(String skill) {
        this.skill = skill;
    }

    /**
     * 获取工作经历
     *
     * @return experience - 工作经历
     */
    public String getExperience() {
        return experience;
    }

    /**
     * 设置工作经历
     *
     * @param experience 工作经历
     */
    public void setExperience(String experience) {
        this.experience = experience;
    }

    /**
     * 获取身高
     *
     * @return height - 身高
     */
    public Integer getHeight() {
        return height;
    }

    /**
     * 设置身高
     *
     * @param height 身高
     */
    public void setHeight(Integer height) {
        this.height = height;
    }

    /**
     * 获取籍贯
     *
     * @return native_place - 籍贯
     */
    public Integer getNativePlace() {
        return nativePlace;
    }

    /**
     * 设置籍贯
     *
     * @param nativePlace 籍贯
     */
    public void setNativePlace(Integer nativePlace) {
        this.nativePlace = nativePlace;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取QQ
     *
     * @return qq - QQ
     */
    public String getQq() {
        return qq;
    }

    /**
     * 设置QQ
     *
     * @param qq QQ
     */
    public void setQq(String qq) {
        this.qq = qq;
    }

    /**
     * 获取学校名称
     *
     * @return school - 学校名称
     */
    public String getSchool() {
        return school;
    }

    /**
     * 设置学校名称
     *
     * @param school 学校名称
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * 获取专业名称
     *
     * @return major - 专业名称
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置专业名称
     *
     * @param major 专业名称
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * 获取求职意向,允许保存多值
     *
     * @return job_intention - 求职意向,允许保存多值
     */
    public String getJobIntention() {
        return jobIntention;
    }

    /**
     * 设置求职意向,允许保存多值
     *
     * @param jobIntention 求职意向,允许保存多值
     */
    public void setJobIntention(String jobIntention) {
        this.jobIntention = jobIntention;
    }

    /**
     * 获取意向工作地点,允许保存多值
     *
     * @return district_intention - 意向工作地点,允许保存多值
     */
    public String getDistrictIntention() {
        return districtIntention;
    }

    /**
     * 设置意向工作地点,允许保存多值
     *
     * @param districtIntention 意向工作地点,允许保存多值
     */
    public void setDistrictIntention(String districtIntention) {
        this.districtIntention = districtIntention;
    }

    /**
     * 获取自我描述
     *
     * @return description - 自我描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置自我描述
     *
     * @param description 自我描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取完善度
     *
     * @return degree - 完善度
     */
    public Integer getDegree() {
        return degree;
    }

    /**
     * 设置完善度
     *
     * @param degree 完善度
     */
    public void setDegree(Integer degree) {
        this.degree = degree;
    }

    /**
     * 获取平台 1 公众号，2 android,3,ios,4,pc 5 pad
     *
     * @return platform - 平台 1 公众号，2 android,3,ios,4,pc 5 pad
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * 设置平台 1 公众号，2 android,3,ios,4,pc 5 pad
     *
     * @param platform 平台 1 公众号，2 android,3,ios,4,pc 5 pad
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取来源id
     *
     * @return source_id - 来源id
     */
    public Long getSourceId() {
        return sourceId;
    }

    /**
     * 设置来源id
     *
     * @param sourceId 来源id
     */
    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    /**
     * 获取百度纬度
     *
     * @return baidu_lat - 百度纬度
     */
    public Double getBaiduLat() {
        return baiduLat;
    }

    /**
     * 设置百度纬度
     *
     * @param baiduLat 百度纬度
     */
    public void setBaiduLat(Double baiduLat) {
        this.baiduLat = baiduLat;
    }

    /**
     * 获取百度经度
     *
     * @return baidu_lon - 百度经度
     */
    public Double getBaiduLon() {
        return baiduLon;
    }

    /**
     * 设置百度经度
     *
     * @param baiduLon 百度经度
     */
    public void setBaiduLon(Double baiduLon) {
        this.baiduLon = baiduLon;
    }

    /**
     * 获取同步到lbs时间
     *
     * @return lbs_sync_time - 同步到lbs时间
     */
    public Date getLbsSyncTime() {
        return lbsSyncTime;
    }

    /**
     * 设置同步到lbs时间
     *
     * @param lbsSyncTime 同步到lbs时间
     */
    public void setLbsSyncTime(Date lbsSyncTime) {
        this.lbsSyncTime = lbsSyncTime;
    }

    /**
     * 获取地图展示 1是 2否
     *
     * @return lbs_show - 地图展示 1是 2否
     */
    public Integer getLbsShow() {
        return lbsShow;
    }

    /**
     * 设置地图展示 1是 2否
     *
     * @param lbsShow 地图展示 1是 2否
     */
    public void setLbsShow(Integer lbsShow) {
        this.lbsShow = lbsShow;
    }

    /**
     * 获取来源type
     *
     * @return source_type - 来源type
     */
    public Integer getSourceType() {
        return sourceType;
    }

    /**
     * 设置来源type
     *
     * @param sourceType 来源type
     */
    public void setSourceType(Integer sourceType) {
        this.sourceType = sourceType;
    }

    /**
     * 获取是否已经有对应的 oppuser 0否 1是
     *
     * @return turn_oppuser - 是否已经有对应的 oppuser 0否 1是
     */
    public Integer getTurnOppuser() {
        return turnOppuser;
    }

    /**
     * 设置是否已经有对应的 oppuser 0否 1是
     *
     * @param turnOppuser 是否已经有对应的 oppuser 0否 1是
     */
    public void setTurnOppuser(Integer turnOppuser) {
        this.turnOppuser = turnOppuser;
    }

    @Override
    public String toString() {
        return "ResumeEntity{" +
                ", city=" + city +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", student=" + student +
                ", birthYear=" + birthYear +
                ", birthMonth=" + birthMonth +
                ", headImg='" + headImg + '\'' +
                ", education=" + education +
                ", workingYear=" + workingYear +
                ", skill='" + skill + '\'' +
                ", experience='" + experience + '\'' +
                ", height=" + height +
                ", nativePlace=" + nativePlace +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", jobIntention='" + jobIntention + '\'' +
                ", districtIntention='" + districtIntention + '\'' +
                '}';
    }
}
