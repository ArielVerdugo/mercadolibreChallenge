package com.example.mercadolibrechallenge.modules.base;


public interface GetBaseCallback<T> {
        void success(T response);
        void error400();
        void errorDefault();
        void errorConnection();
}
