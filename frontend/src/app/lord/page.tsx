
import Footer from '@/components/layout/footer/Footer'
import Header from '@/components/layout/header/Header'
import TableStatic from '@/components/table/TableStatic'
import { authOptions } from '@/lib/auth'
import { getServerSession } from 'next-auth'
import { useSession } from 'next-auth/react'
import { redirect } from 'next/navigation'


const ServerProtectedPage = () => {
  const session = await getServerSession(authOptions)

  if (!session) {
     redirect('http://localhost:3000/')
  }
  return (
     <div className='flex flex-col'>
        <TableStatic/>
        <div>
          Hello Умар
        </div>
      </div>
  )
}

export default ServerProtectedPage