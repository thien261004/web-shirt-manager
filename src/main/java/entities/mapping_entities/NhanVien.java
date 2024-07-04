package entities.mapping_entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class NhanVien {
 private int ID;
 private String ten;
 private String maNV;
 private  String tenDangNhap;
 private String matKhau;
 private int trangThai;

 @Override
 public String toString() {
  return "NhanVien{" +
          "ID=" + ID +
          ", ten='" + ten + '\'' +
          ", maNV='" + maNV + '\'' +
          ", tenDangNhap='" + tenDangNhap + '\'' +
          ", matKhau='" + matKhau + '\'' +
          ", trangThai=" + trangThai +
          '}';
 }
}
