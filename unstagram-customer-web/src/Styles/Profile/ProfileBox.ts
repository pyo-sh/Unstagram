import styled from 'styled-components';

const ProfileBox = styled.div`
    width: 100%;

    flex-direction: column;

    @media(min-width: 736px){
        max-width: 935px;
        height: 150px;
        margin: 0 0 44px 0;
    }
    @media(max-width: 735px){
        height: 82px;
        .Profile-Wrapper{
            margin: 16px 16px 24px 16px;
        }
        .Profile-Detail{
            padding: 0 16px 21px 16px;
            .Profile-Detail-Name{
                font-size: 14px;
            }
            .Profile-Detail-Explain{
                font-size: 14px;
            }
        }
    }
    
    .Profile-About{
        flex-grow: 1;
        height: 100%;
        flex-direction: column;
    }

    .Profile-Detail{
        flex-direction: column;
        font-size: 16px;
        .Profile-Detail-Name{
            margin: 0;
        }
        .Profile-Detail-Explain{

        }
    }
`;

export default ProfileBox;