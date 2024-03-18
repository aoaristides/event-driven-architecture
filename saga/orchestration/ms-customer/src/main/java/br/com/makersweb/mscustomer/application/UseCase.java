package br.com.makersweb.mscustomer.application;

/**
 * @author aaristides
 * @param <IN>
 * @param <OUT>
 */
public abstract class UseCase<IN, OUT> {

    public abstract OUT execute(IN anIn);

}
