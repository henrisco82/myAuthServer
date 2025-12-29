package com.henry.myauthserver;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Disabled("Integration test requiring database connection")
class MyAuthServerApplicationTests {

    @Test
    void contextLoads() {
        // Disabled due to database connection requirements
    }

}
