import groovy.transform.Field
import org.parosproxy.paros.network.HttpMessage
import org.zaproxy.zap.extension.fuzz.httpfuzzer.HttpFuzzResult
import org.zaproxy.zap.extension.fuzz.httpfuzzer.HttpFuzzerTaskProcessorUtils

// Auxiliary variables/constants needed for processing.
@Field final int count = 1

/**
 * Processes the fuzzed message (payloads already injected).
 *
 * Called before forwarding the message to the server.
 *
 * @param {HttpFuzzerTaskProcessorUtils} utils - A utility object that contains functions that ease common tasks.
 * @param {HttpMessage} message - The fuzzed message, that will be forward to the server.
 */
void processMessage(HttpFuzzerTaskProcessorUtils utils, HttpMessage message) {
    // To obtain the list of payloads:
    //    utils.getPayloads()
    // To obtain original message:
    //    utils.getOriginalMessage()
    // To stop fuzzer:
    //    utils.stopFuzzer()
    // To increases the error count with a reason:
    //    utils.increaseErrorCount("Reason Error Message...")
    // To send a message, following redirects:
    //    utils.sendMessage(myMessage)
    // To send a message, not following redirects:
    //    utils.sendMessage(myMessage, false)
    // To add a message previously sent to results:
    //    utils.addMessageToResults("Type Of Message", myMessage)
    // To add a message previously sent to results, with custom state:
    //    utils.addMessageToResults("Type Of Message", myMessage, "Key Custom State", "Value Custom State")
    // The states' value is shown in the column 'State' of fuzzer results tab

    // Process fuzzed message...
    message.getRequestHeader().setHeader("X-Unique-Id", count.toString())
    count++
}

/**
 * Processes the fuzz result.
 *
 * Called after receiving the fuzzed message from the server.
 *
 * @param {HttpFuzzerTaskProcessorUtils} utils - A utility object that contains functions that ease common tasks.
 * @param {HttpFuzzResult} fuzzResult - The result of sending the fuzzed message.
 * @return {boolean} Whether the result should be accepted, or discarded and not shown.
 */
boolean processResult(HttpFuzzerTaskProcessorUtils utils, HttpFuzzResult fuzzResult){
    // All the above 'utils' functions are available plus:
    // To raise an alert:
    //    utils.raiseAlert(risk, confidence, name, description)
    // To obtain the fuzzed message, received from the server:
    //    fuzzResult.getHttpMessage()

    def condition = true
    if (condition){
        fuzzResult.addCustomState("Key Custom State", "Message Contains X")
    }

    return true
}

// Needed for debugging with the DebugWrapper script
// Returns the function, that will be executed by the DebugWrapper script
return [
        processMessage: { utils, message -> processMessage(utils, message) },
        processResult:  { utils, fuzzResult -> processResult(utils, fuzzResult) }
]
