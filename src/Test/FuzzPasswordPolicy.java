package Test;

import com.code_intelligence.jazzer.api.FuzzedDataProvider;
import services.PasswordPolicyService;

public class FuzzPasswordPolicy {

    public static void fuzzerTestOneInput(FuzzedDataProvider data) {

        String password = data.consumeString(50);

        try {

            PasswordPolicyService.isStrongPassword(password);

        }

        catch (Exception e) {

        }

    }
}