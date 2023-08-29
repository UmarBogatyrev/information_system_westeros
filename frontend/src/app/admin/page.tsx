"use client";

import { getSession } from "next-auth/react";
import Head from "next/head";
import { getServerSession } from 'next-auth'
import { redirect, useRouter } from 'next/navigation'
import React, { useEffect } from 'react'
import axios from 'axios'
import toast from 'react-hot-toast'
import Button from '@/components/button/Button'



const AdminPanel = () => {
    const [user, setUser] = React.useState({
        email: "",
        password: "",
        username: "",
    })

    const [loading, setLoading] = React.useState(false);
    const [buttonDisabled, setButtonDisabled] = React.useState(false);

    const onSignup = async () => {
        try {
            setLoading(true);
            const response = await axios.post("/api/singinLord", user);
            console.log("Signup success", response.data);
            
        } catch (error:any) {
            console.log("Signup failed", error.message);
            
            toast.error(error.message);
        }finally {
            setLoading(false);
        }
    }

    useEffect(() => {
        if(user.email.length > 0 && user.password.length > 0 && user.username.length > 0) {
            setButtonDisabled(false);
        } else {
            setButtonDisabled(true);
        }
    }, [user]);

	return (
		<main>
            <header className="relative flex w-full items-center justify-between bg-white py-2 text-neutral-600 shadow-lg hover:text-neutral-700 focus:text-neutral-700 dark:bg-neutral-600 dark:text-neutral-200 md:flex-wrap md:justify-center">
                Панель администратора
            </header>
            <div 
                className="          
                flex  
                justify-center
                mt-[30px] 
                font-semibold 
                font-sans
                "
            >
                <div 
                className="
                    flex
                    flex-col-reverse
                    bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90%
                    px-4
                    py-10
                    mb-[304px]
                    shadow
                    sm:rounded-lg
                    sm:px-10
                ">
                    <form 
                        className="
                        flex
                        flex-col 
                        space-y-6 " 
                    >
                        <h1 className='text-center'>{loading ? "Processing" : "Регистрация лордов"}</h1>
                        <hr />
                        <label 
                            htmlFor="username"
                            className="
                                block 
                                text-xm
                                font-medium 
                                leading-6 
                                text-gray-900
                                ">
                                    Username
                        </label>
                        <input 
                            className="p-2 border border-gray-300 rounded-lg mb-4 focus:outline-none focus:border-gray-600 text-black"
                            id="username"
                            type="text"
                            value={user.username}
                            onChange={(e) => setUser({...user, username: e.target.value})}
                            placeholder="Введите имя"
                        />
                        <label 
                            htmlFor="email"
                            className="
                                block 
                                text-xm
                                font-medium 
                                leading-6 
                                text-gray-900
                                ">
                                Email
                        </label>
                        <input 
                            className="p-2 border border-gray-300 rounded-lg mb-4 focus:outline-none focus:border-gray-600 text-black"
                            id="email"
                            type="text"
                            value={user.email}
                            onChange={(e) => setUser({...user, email: e.target.value})}
                            placeholder="Введите email"
                        />
                        <label 
                            htmlFor="password"
                            className="
                                block 
                                text-xm
                                font-medium 
                                leading-6 
                                text-gray-900
                                ">
                                Пароль                  
                            </label>
                        <input 
                            className="p-2 border border-gray-300 rounded-lg mb-4 focus:outline-none focus:border-gray-600 text-black"
                            id="password"
                            type="password"
                            value={user.password}
                            onChange={(e) => setUser({...user, password: e.target.value})}
                            placeholder="Введите пароль"
                        />
                        <Button onClick = {onSignup}disabled={loading} fullWidth type="submit">
                            {buttonDisabled ? "Введены не все данные" : "Зарегистрировать"}
                        </Button>
                    </form>
                </div>
            </div>
		</main>
	);
}

export default AdminPanel
