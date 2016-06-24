package services

import play.api.Application


/**
  * Created by xjpz9 on 2016/6/13.
  */

object ViewAccessPoint {
    private val myDaoCache = Application.instanceCache[MessageServices]

    object Implicits {
        implicit def myDao(implicit application: Application): MessageServices = myDaoCache(application)
    }
}
