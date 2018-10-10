package com.xiguo.www.group;

import com.xiguo.www.group.utils.Security.AES;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class test {

    public static void DeDemo () {
        String sessionKey = "W14ktD3zdDXsp6749N5Abw==";
        String iv = "yYxBNg7lcEcZ7cGB3s6YBg==";
//        String content = "rFxAzf9z4hj1NAHWGI0Gvk372adPicCzvQs2Qq9x0Mkh7cV/BOK3FCoZsku1NwrC3me1Fz9ykdU8c2iD74LFsxfc9tUDPEcdz3oiIuETN7xSgWX+anNDHpMB7eqxbHtwpS/Mk2u5I4fM2XHV1i6fxr12kc0qN6lMBTf1ERFz9IRwkoVVk3wxB5d9Cbyeq6xEQyuuRnj4TJ6ViGhlMH5miQ==";
        String content = "iolxXA4bm5JjfyHNmP1CpTQvOHtry4pSklHqXCg6PFYtDW1Yi vTg3xW9VzFRKnjZ1q9T bDRKX9p7d2i0fTZtkNWihy5FiIbLI0XGEgL 7OIzkmcXa5kdUwIROKBH428EFKzwY97sRvrNcgDu0SUu8jdkZtmyRd4FjST2fGIIBsxgolrmvuT6qpt74OetJbuyyVYQUT2gYydfsygpQ WQ==";
        content = content.replace(" ", "+");
        //        String content = "iolxXA4bm5JjfyHNmP1CpTQvOHtry4pSklHqXCg6PFYtDW1Yi+vTg3xW9VzFRKnjZ1q9T+bDRKX9p7d2i0fTZtkNWihy5FiIbLI0XGEgL+7OIzkmcXa5kdUwIROKBH428EFKzwY97sRvrNcgDu0SUu8jdkZtmyRd4FjST2fGIIBsxgolrmvuT6qpt74OetJbuyyVYQUT2gYydfsygpQ+WQ==";

        byte[] dateByte = Base64.getDecoder().decode(content);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] keyByte = Base64.getDecoder().decode(sessionKey);
        byte[] decrypt = AES.decrypt(dateByte, keyByte, ivByte);
        System.out.println("解码内容:" + decrypt);
        System.out.println("解码内容:" + new String(decrypt, StandardCharsets.UTF_8));
    }

    public static void main(String[] args) {
        DeDemo();
    }
}
