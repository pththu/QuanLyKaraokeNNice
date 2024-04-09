package entities;

import java.util.Objects;

public class LoaiPhong {
	private String maLoai;
	private String tenLoaiPhong;

	public LoaiPhong(String maLoai, String tenLoaiPhong) {
		super();
		this.maLoai = maLoai;
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public LoaiPhong(String tenLoaiPhong) {
		super();
		this.tenLoaiPhong = tenLoaiPhong;
	}

	public String getMaLoai() {
		return maLoai;
	}

	public void setMaLoai(String maLoai) {
		this.maLoai = maLoai;
	}

	public String getTenLoaiPhong() {
		return tenLoaiPhong;
	}

	public void setTenLoaiPhong(String tenLoaiPhong) {
		this.tenLoaiPhong = tenLoaiPhong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maLoai, tenLoaiPhong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		LoaiPhong other = (LoaiPhong) obj;
		return Objects.equals(maLoai, other.maLoai) || Objects.equals(tenLoaiPhong, other.tenLoaiPhong);
	}

	@Override
	public String toString() {
		return "LoaiPhongEntity [maLoai=" + maLoai + ", tenLoaiPhong=" + tenLoaiPhong + "]";
	}
}
