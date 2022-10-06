package fc.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class ScanResult {

    private String scan_id;
    private String engine;
    private Boolean detected;
    private String version;
    private String result;
    private LocalDateTime update;
}
