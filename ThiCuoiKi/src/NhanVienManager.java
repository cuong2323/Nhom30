
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NhanVienManager {

    // Lấy tất cả nhân viên
    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT * FROM NhanVien";

        try (Connection conn = DatabaseConnection.getConnection(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("MaNhanVien"), // Lấy giá trị kiểu int từ cơ sở dữ liệu
                        rs.getString("TenNhanVien"),
                        rs.getString("BoPhan"),
                        rs.getString("ChucVu"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("SoDienThoai")
                );
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    // Thêm nhân viên
    public boolean addNhanVien(int maNhanVien, String tenNhanVien, String boPhan, String chucVu, String gioiTinh, String email, String soDienThoai) {
        String query = "INSERT INTO NhanVien (MaNhanVien, TenNhanVien, BoPhan, ChucVu, GioiTinh, Email, SoDienThoai) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            // Thiết lập giá trị cho các tham số
            stmt.setInt(1, maNhanVien);  // Sử dụng setInt() vì MaNhanVien là kiểu int
            stmt.setString(2, tenNhanVien);
            stmt.setString(3, boPhan);
            stmt.setString(4, chucVu);
            stmt.setString(5, gioiTinh);
            stmt.setString(6, email);
            stmt.setString(7, soDienThoai);

            // Thực thi câu lệnh SQL
            return stmt.executeUpdate() > 0;  // Trả về true nếu thêm thành công
        } catch (SQLException ex) {
            ex.printStackTrace();  // In ra chi tiết lỗi nếu có
        }
        return false;  // Trả về false nếu có lỗi
    }

    // Cập nhật nhân viên
    public boolean updateNhanVien(int maNhanVien, String tenNhanVien, String boPhan, String chucVu, String gioiTinh, String email, String soDienThoai) {
        String query = "UPDATE NhanVien SET TenNhanVien = ?, BoPhan = ?, ChucVu = ?, GioiTinh = ?, Email = ?, SoDienThoai = ? WHERE MaNhanVien = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, tenNhanVien);
            stmt.setString(2, boPhan);
            stmt.setString(3, chucVu);
            stmt.setString(4, gioiTinh);
            stmt.setString(5, email);
            stmt.setString(6, soDienThoai);
            stmt.setInt(7, maNhanVien);  // Sử dụng setInt() vì MaNhanVien là kiểu int
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Xóa nhân viên
    public boolean deleteNhanVien(int maNhanVien) {
        String query = "DELETE FROM NhanVien WHERE MaNhanVien = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maNhanVien);  // Sử dụng setInt() vì MaNhanVien là kiểu int
            return stmt.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    // Tìm kiếm nhân viên theo string
    public List<NhanVien> searchNhanVien(String searchQuery) {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT * FROM NhanVien WHERE TenNhanVien LIKE ? OR BoPhan LIKE ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            String searchParam = "%" + searchQuery + "%";  // Thêm dấu '%' để tìm kiếm theo chuỗi
            stmt.setString(1, searchParam);
            stmt.setString(2, searchParam);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("MaNhanVien"),
                        rs.getString("TenNhanVien"),
                        rs.getString("BoPhan"),
                        rs.getString("ChucVu"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("SoDienThoai")
                );
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
//tim kiem theo int 
    public List<NhanVien> searchNhanVien(int maNhanVien) {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, maNhanVien);  // Tìm kiếm theo mã nhân viên (int)
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien(
                        rs.getInt("MaNhanVien"),
                        rs.getString("TenNhanVien"),
                        rs.getString("BoPhan"),
                        rs.getString("ChucVu"),
                        rs.getString("GioiTinh"),
                        rs.getString("Email"),
                        rs.getString("SoDienThoai")
                );
                list.add(nv);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}
