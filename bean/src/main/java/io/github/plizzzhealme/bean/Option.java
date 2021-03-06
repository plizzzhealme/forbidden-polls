package io.github.plizzzhealme.bean;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class Option implements Serializable {

    @Serial
    private static final long serialVersionUID = -4859356782695712359L;

    private int id;
    private String body;
    private int index;
    private int count;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Option option = (Option) o;

        return getId() == option.getId()
                && getIndex() == option.getIndex()
                && getCount() == option.getCount()
                && Objects.equals(getBody(), option.getBody());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getBody(), getIndex(), getCount());
    }

    @Override
    public String toString() {
        return "Option{" +
                "id=" + id +
                ", body='" + body + '\'' +
                ", index=" + index +
                ", count=" + count +
                '}';
    }
}
