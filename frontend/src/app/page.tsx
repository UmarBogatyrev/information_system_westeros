
import AuthForm from '@/components/authentication/AuthForm'
import Image from "next/image";
import Link from 'next/link'

const handleClick = () => {
	window.location.href = '/login';
}


const HomePage = () => {
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
          h-screen
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
              Добро пожаловать
          </h2>
	
        </div>
		<div className='justify-center flex m-5'>
			<Link href="/login"
					className="
					bg-gradient-to-r from-green-400 to-blue-500 hover:from-pink-500 hover:to-yellow-500 
					px-3 
					py-2 
					text-center 
					rounded-md 
					text-sm 
					font-semibold 
					">
					Начать работу
			</Link>
		</div>
   </div>
  </main>
  )
}

export default HomePage