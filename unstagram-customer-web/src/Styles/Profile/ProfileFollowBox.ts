import styled from 'styled-components';

const ProfileFollowBox = styled.ul`
    width: 100%;
    @media(min-width: 736px){
        height: 20px;
        padding: 0;
        margin: 0 0 20px 0;

        .ProfileFollow-List{
            height: 36px;

            margin: 0 40px 0 0;
        }
    }
    @media(max-width: 735px){
        height: 61px;
        padding: 12px 0;
        margin: 0;
        border-top: 1px solid rgba(var(--b38,219,219,219),1);
        font-size: 14px;

        .ProfileFollow-List{
            width: 33.33%;

            flex-direction: column;
            justify-content: center;
            align-items: center;

            color: rgba(var(--f52,142,142,142),1);
        }
        .ProfileFollow-Link{
            flex-direction: column;
            justify-content: center;
            align-items: center;
        }
    }
    .ProfileFollow-Count{
        font-weight: 600;

        color: black;
    }
`;

export default ProfileFollowBox;