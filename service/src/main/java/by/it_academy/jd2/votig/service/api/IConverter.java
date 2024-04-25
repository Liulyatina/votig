package by.it_academy.jd2.votig.service.api;

public interface IConverter<FROM, TO> {
    TO convert(FROM item);
}
