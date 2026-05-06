package Domain.valueObjects;


import org.springframework.util.Assert;

public record PatternCammo(String imagePath, String name) {
    public PatternCammo {
        Assert.hasText(name, "name must not be blank");
        if (imagePath != null) {
            Assert.hasText(imagePath, "imagePath must not be blank if provided");
        }
    }
}