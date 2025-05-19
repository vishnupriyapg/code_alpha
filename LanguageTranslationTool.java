import com.google.cloud.translate.Translate;
import com.google.cloud.translate.TranslateOptions;
import com.google.cloud.translate.Translation;

import java.util.Scanner;

/**
 * Language Translator using Google Cloud Translation API.
 * Translates text from source to target language.
 * 
 * Required:
 * - Google Cloud account
 * - Enable Cloud Translation API
 * - Set environment variable:
 *   GOOGLE_APPLICATION_CREDENTIALS=path/to/key.json
 */
public class LanguageTranslator {

    // Display ISO 639-1 language codes
    private static void showLanguageHelp() {
        System.out.println("🔤 Common Language Codes:");
        System.out.println("  en - English");
        System.out.println("  fr - French");
        System.out.println("  es - Spanish");
        System.out.println("  de - German");
        System.out.println("  hi - Hindi");
        System.out.println("  ja - Japanese");
        System.out.println("  zh - Chinese (Simplified)");
        System.out.println("  ar - Arabic");
        System.out.println("  ru - Russian");
        System.out.println("----------------------------");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== 🌍 AI Language Translator ===\n");

        showLanguageHelp();

        System.out.print("Enter text to translate: ");
        String inputText = scanner.nextLine();

        if (inputText.trim().isEmpty()) {
            System.out.println("⚠️  Text cannot be empty.");
            return;
        }

        System.out.print("Enter target language code: ");
        String targetLang = scanner.nextLine();

        if (targetLang.trim().isEmpty()) {
            System.out.println("⚠️  Language code cannot be empty.");
            return;
        }

        try {
            // Authenticate using default credentials
            Translate translate = TranslateOptions.getDefaultInstance().getService();

            // Translate the text
            Translation translation = translate.translate(
                    inputText,
                    Translate.TranslateOption.targetLanguage(targetLang),
                    Translate.TranslateOption.model("nmt") // Neural Machine Translation
            );

            // Output result
            System.out.println("\n✅ Translated Text: " + translation.getTranslatedText());

        } catch (Exception e) {
            System.out.println("❌ Error during translation: " + e.getMessage());
            e.printStackTrace();
        }

        System.out.println("\nThank you for using the Language Translator!");
    }
}
