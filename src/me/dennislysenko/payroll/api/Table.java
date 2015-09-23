package me.dennislysenko.payroll.api;

import java.util.ArrayList;
import java.util.List;

public class Table {

	public List<String> headers = new ArrayList<String>();
	public List<List<String>> datas = new ArrayList<List<String>>();

	public List<Integer> columsLengths;
	public Integer leftMargin = 0;
	public Integer rightMargin = 0;

	public Table(String[] headers) {
		for (String header : headers) {
			this.headers.add(header);
			datas.add(new ArrayList<String>());
		}
	}

	public void addData(Integer col, String data) {
		datas.get(col).add(data);
	}
	
	public void setMarginRight(Integer margin) {
		if (margin > 0) {
			rightMargin = margin;
		}
	}
	
	public void setMarginLeft(Integer margin) {
		if (margin > 0) {
			leftMargin = margin;
		}
	}

	public void print() {
		setHeaderLengths();
		printSeperator();
		printLine(headers);
		printSeperator();
		List<List<String>> rows = convertColToRow();
		for (List<String> row : rows) {
			printLine(row);
		}
		printSeperator();
	}

	private List<List<String>> convertColToRow() {
		List<List<String>> output = new ArrayList<List<String>>();
		Integer rowLength = 0;
		for (List<String> data : datas) {
			if (rowLength < data.size()) {
				rowLength = data.size();
			}
		}
		for (int i = 0; i < rowLength; i++) {
			output.add(new ArrayList<String>());
			for (int j = 0; j < datas.size(); j++) {
				try {
					output.get(i).add(datas.get(j).get(i));
				} catch (IndexOutOfBoundsException ex) {
					output.get(i).add("");
				}
			}
		}
		return output;
	}

	private void printLine(List<String> line) {
		for (int i = 0; i < line.size(); i++) {
			System.out.print("|");
			for (int j = 0; j < leftMargin; j++) {
				System.out.print(" ");
			}
			Integer leftover = columsLengths.get(i) - line.get(i).length();
			System.out.print(line.get(i));
			for (int j = 0; j < leftover + rightMargin; j++) {
				System.out.print(" ");
			}
		}
		System.out.print("|\n");
	}

	private void printSeperator() {
		for (int i = 0; i < headers.size(); i++) {
			System.out.print("+");
			for (int j = 0; j < columsLengths.get(i) + leftMargin + rightMargin; j++) {
				System.out.print("-");
			}
		}
		System.out.print("+\n");
	}

	private void setHeaderLengths() {
		List<Integer> lengths = new ArrayList<Integer>();
		for (String header : headers) {
			lengths.add(header.length());
		}
		for (int i = 0; i < datas.size(); i++) {
			for (String row : datas.get(i)) {
				if (lengths.get(i) < row.length()) {
					lengths.set(i, row.length());
				}
			}
		}
		columsLengths = lengths;
	}

}
