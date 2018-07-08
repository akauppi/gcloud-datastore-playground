package main

import com.google.cloud.datastore._

import collection.JavaConverters._

import com.typesafe.scalalogging._

/*
* Write to the Datastore (with batch write)
*/
object MainWrite extends LazyLogger {
  import System.{currentTimeMillis => nowMs}

  def main(args: Array[String]): Unit = {
    val ds: Datastore = DatastoreOptions.getDefaultInstance.getService()

    val kf: KeyFactory = ds.newKeyFactory().setKind("MainWrite")
    val key: Key = kf.newKey("abc")

    val ents: Seq[Entity] = (0 to 3).map{ i =>
      createEntity(key, i)
    }

    logger.info("Going to write")
    val t0: Long = nowMs()

    ds.put(ents:_*)   // handled as a batch write

    logger.info(s"Wrote (${nowMs - t0}ms)")
  }

  def createEntity(key: Key, i: Int): Entity = {

    Entity.newBuilder(key)
      .set("value", i)
      .set("author","johndoe")
      .build()
  }
}
