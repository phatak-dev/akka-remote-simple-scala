package com.madhukaraphatak.akka.remote

import java.io.File

import akka.actor._
import com.typesafe.config.ConfigFactory

/**
 * Created by madhu on 23/12/14.
 */
class RemoteActor extends Actor {
  override def receive: Receive = {
    case msg: String => {
      println("remote received " + msg + " from " + sender)
      sender ! "hi"
    }
    case _ => println("Received unknown msg ")
  }
}

object RemoteActor{
  def main(args: Array[String]) {
    val configFile = getClass.getClassLoader.getResource("remote_application.conf").getFile
    val config = ConfigFactory.parseFile(new File(configFile))
    val system = ActorSystem("RemoteSystem" , config)
    val remote = system.actorOf(Props[RemoteActor], name="remote")
    println("remote is ready")


  }
}


