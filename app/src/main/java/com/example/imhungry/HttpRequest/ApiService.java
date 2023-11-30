package com.example.imhungry.HttpRequest;

import com.example.imhungry.Domain.Estudiantes;
import com.example.imhungry.Domain.Comprador;
import com.example.imhungry.Domain.ProductosFavoritos;
import com.example.imhungry.Domain.Pedido;
import com.example.imhungry.Domain.Producto;
import com.example.imhungry.Domain.Valoracion;
import com.example.imhungry.Domain.Vendedor;
import com.example.imhungry.Domain.Venta;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;


public interface ApiService {
    // Compradores
    @GET("compradores/")
    Call<Comprador[]> getCompradores();

    @POST("compradores/")
    Call<Comprador> crearComprador(@Body Comprador comprador);

    @DELETE("compradores/{id}")
    Call<Comprador> eliminarComprador(@Path("id") String id);

    // Estudiantes
    @POST("estudiantes/login")
    Call<Estudiantes> estudiantesLogin(@Body Estudiantes credenciales);

    @GET("estudiantes/all")
    Call<Estudiantes[]> estudiantesGetAll();

    @GET("estudiantes/{id}")
    Call<Estudiantes> estudiantesGetById(@Path("id") String id);

    @POST("estudiantes/")
    Call<Estudiantes> estudiantesCreate(@Body Estudiantes estudiante);

    @PUT("estudiantes/{id}")
    Call<Estudiantes> estudiantesUpdate(@Path("id") String id, @Body Estudiantes estudiante);

    @PATCH("estudiantes/{id}")
    Call<Estudiantes> estudiantesUpdatePass(@Path("id") String id, @Body Estudiantes contraseña);

    @DELETE("estudiantes/{id}")
    Call<Estudiantes> estudiantesDelete(@Path("id") String id);

    // Pedidos
    @GET("pedidos/all")
    Call<Pedido[]> pedidosGetAll();

    @GET("pedidos/{name}")
    Call<Pedido> pedidosGetById(@Path("name") String name);

    @POST("pedidos/")
    Call<Pedido> pedidosCreate(@Body Pedido pedido);

    @PUT("pedidos/{id}")
    Call<Pedido> pedidosUpdate(@Path("id") String id, @Body Pedido pedido);

    // Producto
    @GET("producto/")
    Call<Producto[]> productoGetAll();

    @GET("producto/{name}")
    Call<Producto> productoGetByName(@Path("name") String name);

    @POST("producto/")
    Call<Producto> productoCreate(@Body Producto producto);

    @PUT("producto/{id}")
    Call<Producto> productoUpdate(@Path("id") String id, @Body Producto producto);

    // ProductosFavoritos
    @GET("productosFavoritos/")
    Call<ProductosFavoritos[]> productosFavGetAll();

    @POST("productosFavoritos/")
    Call<ProductosFavoritos> productosFavCreate(@Body ProductosFavoritos productosFavoritos);

    @DELETE("productosFavoritos/{id}")
    Call<ProductosFavoritos> productosFavDelete(@Path("id") String id);

    // Valoracion
    @GET("valoracion/")
    Call<Valoracion[]> valoracionGetAll();

    @POST("valoracion/")
    Call<Valoracion> valoracionCreate(@Body Valoracion valoracion);

    @PUT("valoracion/{id}")
    Call<Valoracion> valoracionUpdate(@Path("id") String id, @Body Valoracion valoracion);

    // Vendedor
    @GET("vendedor/")
    Call<Vendedor[]> vendedorGetAll();

    @POST("vendedor/")
    Call<Vendedor> vendedorCreate(@Body Vendedor vendedor);

    @DELETE("vendedor/{id}")
    Call<Vendedor> vendedorDelete(@Path("id") String id);

    // Venta
    @GET("venta/")
    Call<Venta[]> ventaGetAll();

    @POST("venta/")
    Call<Venta> ventaCreate(@Body Venta venta);

}
