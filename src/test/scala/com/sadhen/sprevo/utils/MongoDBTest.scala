package com.sadhen.sprevo.utils

import org.scalatest.{FlatSpec, Matchers}

/**
  * Created by rendong on 16/12/4.
  */
class MongoDBTest extends FlatSpec with Matchers {
  it should "print by index" in {
    MongoDB.getDocumentByIndex("salut").foreach(println)
  }

  it should "print by name" in {
    MongoDB.getDocumentByName("pomo").foreach(println)
  }
}
