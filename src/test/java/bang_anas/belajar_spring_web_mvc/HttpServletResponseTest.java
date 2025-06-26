import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

@SpringBootTest
@AutoConfigureMockMvc
public class HttpServletResponseTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testHttpServletResponseContent() throws Exception {
        MvcResult result = mockMvc.perform(get("/hello"))
                .andReturn();

        MockHttpServletResponse response = result.getResponse();

        // ✅ Inilah yang bisa kamu eksplor:
        System.out.println("Status      : " + response.getStatus());
        System.out.println("ContentType : " + response.getContentType());
        System.out.println("Headers     : " + response.getHeaderNames());
        System.out.println("Body        : " + response.getContentAsString());
    }
}
