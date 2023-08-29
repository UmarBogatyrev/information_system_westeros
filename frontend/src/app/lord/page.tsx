import Image from "next/image";
import AuthForm from "../../components/authentication/AuthForm";
import TableStatic from '@/components/table/TableStatic'

const HomePageLord = () => {
  return (
    <div className='flex flex-col'>
       <TableStatic/>
       <div>
         Hello Умар
       </div>
     </div>
 )
}

export default HomePageLord;
