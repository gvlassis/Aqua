package com.OutOfTheBox.Aqua;

import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class VoiceRecorder {
    public static String record(LiveSpeechRecognizer recognizer)
    {
        SpeechResult result = recognizer.getResult();
        while (result.getHypothesis().isEmpty()) {
            System.out.println("Didn't understand");
            result = recognizer.getResult();
        }

        return result.getHypothesis();
    }

    public static void configure(Configuration config, boolean isGreek)
    {
        if (isGreek)
        {
            config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/el-gr");
            config.setDictionaryPath("resource:/edu/cmu/sphinx/models/el-gr/el-gr.dict");
            config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/el-gr/el-gr.lm.bin");
        }
        else
        {
            config.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
            config.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
            config.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");
        }


        config.setUseGrammar(false);
    }

}
