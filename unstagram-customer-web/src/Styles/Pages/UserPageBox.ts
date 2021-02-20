import styled from 'styled-components';

const UserPageBox = styled.div`
    max-width: 935px;

    @media(min-width: 736px){
        padding: 30px 20px 0 20px;
        margin: auto;
    }
    @media(max-width: 735px){
        padding: none;
    }

    flex-direction: column;
    align-items: center;
`;

export default UserPageBox;