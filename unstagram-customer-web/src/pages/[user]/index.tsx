import { GetStaticPaths, GetStaticProps } from 'next';
import { useRouter } from 'next/router';
import Profile from 'Components/Profile/Profile'

// export const getStaticPaths: GetStaticPaths = async (context) => {
//   return {
//     paths: [
//       // { params: { ... } }, // See the "paths" section below
//       { params: { user: '1' } }, // pages/post/[id].tsx
//     ],
//     fallback: false // See the "fallback" section below
//   };
// }

// export const getStaticProps: GetStaticProps = async (context) => {
//   // const res = await fetch(`...url/${params.id}`)
//   // const data = await res.json()
//   // 데이터를 props로 전달 하며, 요청 시 1초에 한번 다시 패칭
//   return {
//     props: {
//       data: context.params ? context.params.user : null
//     },
//     revalidate: 1,
//   }
// }

const UserPage = () => {
  const router = useRouter();
  const { user } = router.query;

  return (
    <>
    {user}
    {router.asPath}
    <Profile/>
    </>
  );
};

export default UserPage;