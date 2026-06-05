package Test;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import services.AuthService;
import security.PasswordHasher;

public class FuzzLogin {

    public static void fuzzerTestOneInput(FuzzedDataProvider data) {

        String enteredId =
                data.consumeString(20);

        String enteredPassword =
                data.consumeString(50);

        try {

            AuthService.validateLogin(
                    enteredId,
                    enteredPassword,
                    "999",
                    PasswordHasher.hashPassword("Admin@123")
            );

        }

        catch (Exception e) {

        }

    }

}