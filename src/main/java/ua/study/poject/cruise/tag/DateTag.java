package ua.study.poject.cruise.tag;

import org.apache.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class DateTag extends TagSupport {

    private static final Logger LOGGER = Logger.getLogger(DateTag.class);

    private LocalDateTime value;

    public void setValue(LocalDateTime value) {
        this.value = value;
    }

    @Override
    public int doStartTag() {
        JspWriter writer = pageContext.getOut();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (value != null)
            try {
                writer.write(formatter.format(value));
            } catch (IOException e) {
                LOGGER.error(e);
            }
        return SKIP_BODY;
    }

}
