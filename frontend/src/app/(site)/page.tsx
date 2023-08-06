import Image from "next/image";
import AuthForm from "./components/AuthForm";

const Auth = () => {
  return (
    <main>
      <div 
        className="
          flex 
          min-w-full
          min-h-full 
          flex-col 
          justify-center 
          py-12 
          sm:px-6 
          lg:px-8 
          bg-gradient-to-r from-indigo-500 from-10% via-sky-500 via-30% to-emerald-500 to-90%
        "
      >
        <div className="sm:mx-auto sm:w-full sm:max-w-md">
          <Image
            height="150"
            width="150"
            className="mx-auto w-auto"
            src="/images/Sigil(1).png"
            alt="Logo"
          />
          <h2 
            className="
              mt-6 
              text-center 
              text-3xl 
              font-bold 
              tracking-tight 
              text-gray-900

            "
            >
              Войдите в свою учетную запись
          </h2>
        </div>
        <AuthForm />      
  </div>
  </main>
  )
}

export default Auth;
