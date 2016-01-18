package two_millionaires

import akka.actor.{Props, ActorSystem}
import akka.testkit.{ImplicitSender, TestKit}
import org.scalatest.{BeforeAndAfterAll, Matchers, WordSpecLike}

/**
  * Created by ln.blohin on 18.01.2016.
  */

  object BobAliceTest extends App {
    val system = ActorSystem("BobAliceSystem")
    val alice = system.actorOf(Props[AliceActor], name = "alice")
    val bob = system.actorOf(Props(new BobActor(alice)), name = "bob")

  //Set Millions
    InitMillion.i = 4
    InitMillion.j = 9
    alice ! InitMillion
    bob ! InitMillion

  //Start Prorocol
    bob ! StartBA
  }
