package com.zj.android.api


import com.zj.android.bean.ArticleBean
import com.zj.android.bean.ArticleItemBean
import com.zj.common.bean.BaseBean
import retrofit2.http.GET
import retrofit2.http.Path


/**
 *
 * @ClassName:      Api
 * @Author:         张继
 * @CreateDate:     2021/6/25 16:32
 * @Description:
 */
interface Api {
    //@GET("/api/common/login")
    //fun login(@Query("mobile") mobile: String): Flowable<BaseBean<Any>>


    @GET("article/list/{page}/json")
    suspend fun getArticle(@Path("page") pageNum: Int): BaseBean<ArticleBean>


//    @GET("/api/cnt/xieYi")
//    fun xieYi(): Flowable<BaseBean<AgreeBean>>
//
//    @GET("/api/cnt/yinSi")
//    fun yinSi(): Flowable<BaseBean<AgreeBean>>
//
//    @GET("/api/common/loginPwd")
//    fun loginPwd(
//            @Query("mobile") mobile: String,
//            @Query("password") password: String?
//    ): Flowable<BaseBean<PasswordBean>>
//
//    @GET("/api/common/userPan")
//    fun userPan(@Header("token") token: String): Flowable<BaseBean<Any>>
//
//    /**
//     * 补全信息
//     *
//     * @param token    token
//     * @param avatar   头像
//     * @param nickname 昵称
//     * @param birthday 生日
//     * @param gender   性别
//     * @return 返回信息
//     */
//    @FormUrlEncoded
//    @POST("/api/common/addInfo")
//    fun addInfo(@Header("token") token: String?, @Field("avatar") avatar: String?, @Field("nickname") nickname: String?, @Field("birthday") birthday: String?, @Field("gender") gender: String?): Flowable<BaseBean<Any>>
//
//
//    /**
//     * 图片上传
//     *
//     * @param image 图片路径
//     * @return 返回上传图片后的信息
//     */
//    @Multipart
//    @POST("/api/common/upload")
//    fun upload(@Header("token") token: String?, @Part image: MultipartBody.Part): Flowable<BaseBean<UploadBean>>
//
//    /**
//     * 获取验证码
//     * @param mobile 手机号
//     */
//    @GET("/api/common/faCode")
//    fun faCode(@Query("mobile") mobile: String?): Flowable<BaseBean<Any>>
//
//
//    /**
//     * 发送验证码
//     *
//     * @param mobile
//     * @return
//     */
//    @GET("/api/common/ckCode")
//    fun ckCode(@Query("mobile") mobile: String?, @Query("code") code: String): Flowable<BaseBean<Any>>
//
//
//    /**
//     * 登录,输入验证码
//     *
//     * @param mobile
//     * @return
//     */
//    @GET("/api/common/loginPhone")
//    fun loginPhone(@Query("mobile") mobile: String?, @Query("code") code: String?): Flowable<BaseBean<PasswordBean>>
//
//    /**
//     * 我的爱好
//     *
//     * @param mobile
//     * @return
//     */
//    @GET("/api/common/love")
//    fun love(): Flowable<BaseBean<List<HobbyBean>>>


}