require('dotenv').config();

const express = require('express');
const { Pool } = require('pg');

const app = express();

app.use(express.json());


// =====================================
// CONEXIÓN POSTGRESQL
// =====================================

const pool = new Pool({

    user: process.env.DB_USER,
    password: process.env.DB_PASSWORD,
    host: process.env.DB_HOST,
    port: process.env.DB_PORT,
    database: process.env.DB_NAME

});


// =====================================
// GET PRODUCTOS
// =====================================

app.get('/productos', async (req, res) => {

    try {

        const resultado = await pool.query(
            'SELECT * FROM productos ORDER BY id'
        );

        res.status(200).json({
            ok: true,
            data: resultado.rows
        });

    } catch (error) {

        res.status(500).json({
            ok: false,
            mensaje: error.message
        });

    }

});


// =====================================
// GET PRODUCTO POR ID
// =====================================

app.get('/productos/:id', async (req, res) => {

    try {

        const id = Number(req.params.id);

        const resultado = await pool.query(
            'SELECT * FROM productos WHERE id = $1',
            [id]
        );

        if (resultado.rows.length === 0) {

            return res.status(404).json({
                ok: false,
                mensaje: 'Producto no encontrado'
            });

        }

        res.status(200).json({
            ok: true,
            data: resultado.rows[0]
        });

    } catch (error) {

        res.status(500).json({
            ok: false,
            mensaje: error.message
        });

    }

});


// =====================================
// POST PRODUCTO
// =====================================

app.post('/productos', async (req, res) => {

    try {

        const { nombre, precio } = req.body;


        if (!nombre || precio <= 0) {

            return res.status(400).json({
                ok: false,
                mensaje: 'Datos inválidos'
            });

        }


        const resultado = await pool.query(

            'INSERT INTO productos(nombre, precio) VALUES($1, $2) RETURNING *',

            [nombre, precio]

        );


        res.status(201).json({
            ok: true,
            mensaje: 'Producto creado',
            data: resultado.rows[0]
        });

    } catch (error) {

        res.status(500).json({
            ok: false,
            mensaje: error.message
        });

    }

});


// =====================================
// PUT PRODUCTO
// =====================================

app.put('/productos/:id', async (req, res) => {

    try {

        const id = Number(req.params.id);

        const { nombre, precio } = req.body;


        const resultado = await pool.query(

            `UPDATE productos
             SET nombre = $1, precio = $2
             WHERE id = $3
             RETURNING *`,

            [nombre, precio, id]

        );


        if (resultado.rows.length === 0) {

            return res.status(404).json({
                ok: false,
                mensaje: 'Producto no encontrado'
            });

        }


        res.status(200).json({
            ok: true,
            mensaje: 'Producto actualizado',
            data: resultado.rows[0]
        });

    } catch (error) {

        res.status(500).json({
            ok: false,
            mensaje: error.message
        });

    }

});


// =====================================
// DELETE PRODUCTO
// =====================================

app.delete('/productos/:id', async (req, res) => {

    try {

        const id = Number(req.params.id);


        const resultado = await pool.query(

            'DELETE FROM productos WHERE id = $1 RETURNING *',

            [id]

        );


        if (resultado.rows.length === 0) {

            return res.status(404).json({
                ok: false,
                mensaje: 'Producto no encontrado'
            });

        }


        res.status(200).json({
            ok: true,
            mensaje: 'Producto eliminado'
        });

    } catch (error) {

        res.status(500).json({
            ok: false,
            mensaje: error.message
        });

    }

});


// =====================================
// INICIAR SERVIDOR
// =====================================

app.listen(process.env.PORT, () => {

    console.log(`Servidor funcionando en puerto ${process.env.PORT}`);

});