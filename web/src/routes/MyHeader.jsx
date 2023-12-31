import React, { useState } from 'react';
import { Button, Nav, Navbar, NavbarBrand } from 'reactstrap';
import { Link, useNavigate } from 'react-router-dom';
import LoginModal from '../components/login/LoginModal';
import { isLogin } from '../store/store';
import { logout } from '../utills/userUtils';

const MyHeader = () => {
  const [modal, setModal] = useState(false);
  const { state, setState } = isLogin();
  const nav = useNavigate();
  const logoutHandler = () => {
    logout(setState);
    nav('/');
  };

  const toggle = () => setModal(!modal);
  return (
    <div>
      <>
        <LoginModal toggle={toggle} modal={modal} />
        <Navbar>
          <NavbarBrand>
            <Link to="/">
              <img
                width={150}
                src="https://github.com/pdfolio/web/assets/71807768/be9bd7df-0b32-484b-a1c9-de9a0a1bda1e"
                alt="logo"
              />
            </Link>

            <Link to="/">
              <Button outline style={{ marginRight: '10px', border: 'none' }}>
                Project
              </Button>
            </Link>
            <Link to="/gather">
              <Button outline style={{ marginRight: '10px', border: 'none' }}>
                Gather
              </Button>
            </Link>
          </NavbarBrand>
          <Nav
            style={{
              display: 'flex',
              justifyContent: 'flex-end',
              margin: '1%',
            }}
          >
            {!state.isLogin ? (
              <Button
                onClick={toggle}
                outline
                style={{ marginRight: '10px', border: 'none' }}
              >
                로그인
              </Button>
            ) : (
              <>
                <Link to="/project/write">
                  <Button
                    outline
                    style={{ marginRight: '10px', border: 'none' }}
                  >
                    새 글 쓰기
                  </Button>
                </Link>
                <Link to="/gather/write">
                  <Button
                    outline
                    style={{ marginRight: '10px', border: 'none' }}
                  >
                    팀원 구하기
                  </Button>
                </Link>
                <Link to="/mypage">
                  <Button
                    outline
                    style={{ marginRight: '10px', border: 'none' }}
                  >
                    내정보
                  </Button>
                </Link>
                <Button
                  onClick={logoutHandler}
                  outline
                  style={{ marginRight: '10px', border: 'none' }}
                >
                  로그아웃
                </Button>
              </>
            )}
          </Nav>
        </Navbar>
      </>
    </div>
  );
};

export default MyHeader;
