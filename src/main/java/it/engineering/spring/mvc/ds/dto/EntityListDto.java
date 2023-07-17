package it.engineering.spring.mvc.ds.dto;

import java.util.List;

public class EntityListDto<DTO> {

	private long total;
	private List<DTO> rows;
	
	public EntityListDto() { }

	public EntityListDto(long total, List<DTO> rows) {
		this.total = total;
		this.rows = rows;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public List<DTO> getRows() {
		return rows;
	}

	public void setRows(List<DTO> rows) {
		this.rows = rows;
	}
}