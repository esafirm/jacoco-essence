import io.kotlintest.shouldBe
import io.kotlintest.specs.StringSpec
import nolambda.essence.diff.DefaultCommandExecutor

class DefaultCommandExecutorSpec: StringSpec({

    val commandExecutor = DefaultCommandExecutor()

   "It should return correct value" {
       val returnValue = commandExecutor.invoke("echo a")?.trim()
       returnValue shouldBe "a"
   }
})