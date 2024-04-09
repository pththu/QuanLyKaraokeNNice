package entities;

import java.util.Objects;

public class ChiTietDichVuEntity {
	private String maChiTietDatDichVu;
	private DichVuEntity dichVu;
	private int soLuong;

	public ChiTietDichVuEntity() {
		super();
	}

	public ChiTietDichVuEntity(String maChiTietDatDichVu, DichVuEntity dichVu, int soLuong) {
		super();
		this.maChiTietDatDichVu = maChiTietDatDichVu;
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}

	public ChiTietDichVuEntity(DichVuEntity dichVu, int soLuong) {
		super();
		this.dichVu = dichVu;
		this.soLuong = soLuong;
	}

	public String getMaChiTietDatDichVu() {
		return maChiTietDatDichVu;
	}

	public void setMaChiTietDatDichVu(String maChiTietDatDichVu) {
		this.maChiTietDatDichVu = maChiTietDatDichVu;
	}

	public DichVuEntity getDichVu() {
		return dichVu;
	}

	public void setDichVu(DichVuEntity dichVu) {
		this.dichVu = dichVu;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double tinhThanhTien() {
		return dichVu.getGia() * soLuong;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChiTietDatDichVu);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		ChiTietDichVuEntity other = (ChiTietDichVuEntity) obj;
		return Objects.equals(maChiTietDatDichVu, other.maChiTietDatDichVu);
	}

	@Override
	public String toString() {
		return "ChiTietDatDichVuEntity [maChiTietDatDichVu=" + maChiTietDatDichVu + ", dichVu=" + dichVu + ", soLuong="
				+ soLuong + "]";
	}

}
