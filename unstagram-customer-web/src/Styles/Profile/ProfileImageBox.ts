import styled from 'styled-components';

const ProfileImageBox = styled.div`
    @media(min-width: 736px){
        min-width: 290px;
        height: 150px;
        margin: 0 30px 0 0;

        .ProfileImage-Button{
            width: 150px;
            height: 150px;
        }
    }
    @media(max-width: 735px){
        min-width: 77px;
        height: 77px;
        margin: 0 28px 0 0;

        .ProfileImage-Button{
            width: 100%;
            height: 100%;
        }
    }
    .ProfileImage-Button{
        padding: 0;
        margin: 0 auto;
        background: none;
        border: none;
        outline: none;
    }
    .ProfileImage-Image{
        width: 100%;
        height: 100%;
        border-radius: 50%;
        border: 1px solid #000;
    }
`;

export default ProfileImageBox;