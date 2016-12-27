import java.io.File

class Main {

  companion object {

    @JvmStatic fun main(args: Array<String>) {
      val inputFile = File(Main::class.java.getResource("input.txt").file)

      var supportsTlsCounter = 0
      var supportsSslCounter = 0

      inputFile.forEachLine { ipAddressCandidateString ->
        val ipAddressCandidate = IPAddressCandidate(ipAddressCandidateString)

        if (ipAddressCandidate.supportsTransportLayerSnooping) {
          supportsTlsCounter += 1
        }

        if (ipAddressCandidate.supportsSuperSecretListening) {
          supportsSslCounter += 1
        }
      }

      println("Part 1: $supportsTlsCounter")
      println("Part 2: $supportsSslCounter")
    }

  }

}
