'use client';

import axios from "axios";
import { signIn, useSession } from 'next-auth/react';
import { useCallback, useEffect, useState } from 'react';
import { BsGithub, BsGoogle  } from 'react-icons/bs';
import { FieldValues, SubmitHandler, useForm } from 'react-hook-form';
import { useRouter } from "next/navigation";

import AuthSocialButton from './AuthSocialButton';

import { toast } from "react-hot-toast";
import Button from '../button/Button'
import Input from '../inputs/Input'



const AuthForm = () => {
  const session = useSession();
  const router = useRouter();
  const [isLoading, setIsLoading] = useState(false);

  useEffect(() => {
    if (session?.status === 'authenticated') {
      router.push('/conversations')
    }
  }, [session?.status, router]);


  const {
    register,
    handleSubmit,
    formState: {
      errors,
    }
  } = useForm<FieldValues>({
    defaultValues: {
      name: '',
      email: '',
      password: ''
    }
  });


  return ( 
    <div className="mt-8 sm:mx-auto sm:w-full sm:max-w-md">
      <div 
        className="
          bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90%
          px-4
          py-10
          mb-[304px]
          shadow
          sm:rounded-lg
          sm:px-10
        "
      >
        <form 
          className="space-y-6" 
        >
          <Input 
            disabled={isLoading}
            register={register}
            errors={errors}
            required
            id="email" 
            label="Введите адрес Email" 
            // type="email" 
          />
          <Input 
            disabled={isLoading}
            register={register}
            errors={errors}
            required
            id="password" 
            label="Пароль" 
            type="password"
          />
          <div>
            <Button disabled={isLoading} fullWidth type="submit">
              Войти
            </Button>
          </div>
          <div className='
            text-center
					  md:block
					  text-sm
					  font-semibold
					  py-3
					  px-0
					  text-[#fff]'>
          Для восстановления логина и пароля обратитеcь в службу поддержки
          </div>
        </form>
      </div>
      </div>
  );
}
 
export default AuthForm;
