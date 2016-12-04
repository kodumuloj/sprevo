package com.sadhen.sprevo.utils

import java.util.concurrent.TimeUnit

import org.mongodb.scala._
import org.mongodb.scala.model.Filters._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
  * Created by rendong on 16/12/4.
  */
object MongoDB {
  val mongoClient: MongoClient = MongoClient()
  val database: MongoDatabase = mongoClient.getDatabase("local")
  val collection: MongoCollection[Document] = database.getCollection("revo")

  def insertOne(content: String): Unit = {
    val observable = collection.insertOne(Document(content))
    Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))
  }

  def getDocumentByIndex(index: String): Option[String] = {
    val observable = collection.find(equal("index", index))
    val seq = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))
    seq.headOption.map(_.toJson())
  }

  def getDocumentByName(name: String): Option[String] = {
    val observable = collection.find(equal("derives.name", name))
    val seq = Await.result(observable.toFuture(), Duration(10, TimeUnit.SECONDS))
    seq.headOption.map(_.toJson())
  }
}
