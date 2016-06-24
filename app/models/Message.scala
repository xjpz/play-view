package models

import javax.inject.Inject

import play.api.db.slick.{DatabaseConfigProvider, HasDatabaseConfigProvider}
import slick.driver.JdbcProfile

import scala.concurrent.Future

/**
  * Created by xjpz9 on 2016/6/13.
  */

case class Message(content: Option[String], inittime: Option[Long], updtime: Option[Long], tombstone: Option[Int], id: Option[Long])


class Messages @Inject()(protected val dbConfigProvider: DatabaseConfigProvider) extends HasDatabaseConfigProvider[JdbcProfile] {

    import driver.api._

    class MessagesTable(tag: Tag) extends Table[Message](tag, "news2mood") {
        def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)

        def content = column[Option[String]]("content")

        def inittime = column[Option[Long]]("init_time")

        def updtime = column[Option[Long]]("update_time")

        def tombstone = column[Option[Int]]("tombstone")

        def * = (content,inittime, updtime, tombstone, id) <>(Message.tupled, Message.unapply)
    }

    val table = TableQuery[MessagesTable]

    def retrieve(id: Long): Future[Option[Message]] = {
        db.run(table.filter(_.id === id).result.headOption)
    }
}
