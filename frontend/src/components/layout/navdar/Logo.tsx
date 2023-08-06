import Image from "next/image";
import { useRouter } from "next/navigation";

const Logo = () => {
  return (
	<div className='ml-[150px] mt-0'>
	  <Image
		className="hidden md:block cursor-pointer" 
		src="/images/Logo.png" 
		height="86" 
		width="307" 
		alt="Logo" 
      />
  	</div>
  )
}
 
export default Logo;