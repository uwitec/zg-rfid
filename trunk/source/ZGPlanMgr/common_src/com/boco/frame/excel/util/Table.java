package com.boco.frame.excel.util;

import java.util.*;
//在行提交的时候有唯一约束检查
public class Table {

    private String name;
    private List<Row> rows;
    private Meta meta;

    public Table() {
        if (this.rows == null) {
            this.rows = new ArrayList<Row>();
        }

    }

    public Table(String name) {
        this.name = name;
        if (this.rows == null) {
            this.rows = new ArrayList<Row>();
        }
    }

    public Table(String name, Meta meta) {
        this.name = name;
        this.meta = meta;
        if (this.rows == null) {
            this.rows = new ArrayList<Row>();
        }

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Meta getMeta() {
        return this.meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    public Row newRow() {
        Row row = new Row();
        row.setMeta(this.meta);
        return row;
    }

    public void addRow(Row row) throws DataException {
        for (int i = 0; i < this.meta.getCount(); i++) {
            if (this.meta.get(i).isSINGLE()) {
                for (int j = 0; j < this.rows.size(); j++) {
                    if (rows.get(j).get(i) == row.get(i)) {
                        throw new DataException("the index[" + j + "][" + i +
                                                "],only conce accepted");
                    }
                }
            }
        }
        this.rows.add(row);
    }

    public void clear() {
        this.rows.clear();
    }

    public void removeAt(int index) {
        this.rows.remove(index);
    }

    public Row getRow(int index) {
        return this.rows.get(index);
    }

    public int getRowCount() {
        return this.rows.size();
    }

    public int getColumnCount() {
        return this.meta.getCount();
    }
}
