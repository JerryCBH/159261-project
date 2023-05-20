import java.util.ArrayList;
import java.util.HashMap;

public class SpeechService {
    public static Boolean EnableChatGPT = true;
    public static final String CHATGPT_HAPPY_PROMPT = "say a short sentence when you feel happy or getting rich";
    public static final String CHATGPT_DANGER_PROMPT = "say something when you stepped on a nail";
    public static final String CHATGPT_HEALTH_PROMPT = "say a short sentence when you eat yummy food or recovered health";
    public static final HashMap<SpeechType, String> Conversations;

    static {
        Conversations = new HashMap<>();
        Conversations.put(SpeechType.Happy, "Nice!!");
        Conversations.put(SpeechType.Health, "Yummy!!");
        Conversations.put(SpeechType.Danger, "Ouch!!");
    }

    public static void Say(SpeechType type, ArrayList<AnimationRequest> requests, Player player) {
        String message = "";
        if (type == SpeechType.Happy) {
            if (EnableChatGPT) {
                try {
                    var response = ChatGPTConnector.SendRequestToChatGPT(CHATGPT_HAPPY_PROMPT);
                    message = ChatGPTConnector.ExtractGeneratedMessage(response);
                    if (!(message != null && !message.trim().isEmpty())){
                        message = Conversations.get(SpeechType.Happy);
                    }
                } catch (Exception ex) {
                    System.out.println("ChatGPT error: " + ex.getMessage());
                    message = Conversations.get(SpeechType.Happy);
                }
            } else {
                message = Conversations.get(SpeechType.Happy);
            }
        } else if (type == SpeechType.Health) {
            if (EnableChatGPT) {
                try {
                    var response = ChatGPTConnector.SendRequestToChatGPT(CHATGPT_HEALTH_PROMPT);
                    message = ChatGPTConnector.ExtractGeneratedMessage(response);
                    if (!(message != null && !message.trim().isEmpty())){
                        message = Conversations.get(SpeechType.Health);
                    }
                } catch (Exception ex) {
                    System.out.println("ChatGPT error: " + ex.getMessage());
                    message = Conversations.get(SpeechType.Health);
                }
            } else {
                message = Conversations.get(SpeechType.Health);
            }
        } else if (type == SpeechType.Danger) {
            if (EnableChatGPT) {
                try {
                    var response = ChatGPTConnector.SendRequestToChatGPT(CHATGPT_DANGER_PROMPT);
                    message = ChatGPTConnector.ExtractGeneratedMessage(response);
                    if (!(message != null && !message.trim().isEmpty())){
                        message = Conversations.get(SpeechType.Danger);
                    }
                } catch (Exception ex) {
                    System.out.println("ChatGPT error: " + ex.getMessage());
                    message = Conversations.get(SpeechType.Danger);
                }
            } else {
                message = Conversations.get(SpeechType.Danger);
            }
        }

        var speech = new AnimationRequest(WorldObjectType.SpeechBubble, player.GetOccupiedCells()[0], 5);
        speech.Text = message;
        speech.Player = player;
        requests.add(speech);
    }
}
