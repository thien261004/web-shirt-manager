package entities.mapping_entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor@AllArgsConstructor

public class KhachHang {
    private int id;
    private String ten;
    private String sdt;

    private String ma;
    private int trangThai;

}
