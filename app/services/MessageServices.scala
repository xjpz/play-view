package services

import javax.inject.Inject

import models.Messages

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by xjpz9 on 2016/6/13.
  */

class MessageServices @Inject()(messages: Messages) {

    def retrieve(id:Long) = Await.result(messages.retrieve(id),Duration.Inf)

}


