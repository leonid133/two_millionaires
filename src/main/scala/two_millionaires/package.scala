import akka.actor.{Props, ActorSystem}

/**
  * Created by ln.blohin on 18.01.2016.
  */
package object two_millionaires {

  case object FirstMsgBtoA
  {
    var k = 0 // k - j + 1 Bob sent to Alice
  }

  case object FirstMsgAtoB
  {
    var zu:Array[Int] = new Array[Int](10) //zu u=1,2,..10 Alice sent to Bob
    var pp:Int = 0 //N/2 bit prime Alice sent to Bob
  }

  case object SecondMsgBtoA
  {
    var answer:Boolean = false // Answer i>=j true or i<j false Bob sent to Alice
  }

  case object InitMillion{
    var i = 0 //i>1
    var j = 0 //j<10
  }

  case object StartBA
  case object Stop

}
