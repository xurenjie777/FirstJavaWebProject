import './login.css';
import React, { Component } from 'react';
import axios from 'axios';
import {message} from 'antd';

export default class App extends Component{
  constructor (props) {
    super(props);
    this.state = {
      name:'',
      password:'',
    }
  }



  render() {

    return (

        <div id="login-box">
          <h1>Login</h1>
          <div className="form">
            <div className="item">
              <i className="fa fa-user-circle-o" aria-hidden="true"></i>
              <input allowClear={true} type="text" placeholder="Username" value={this.state.name} onChange={(e) => {
                this.setState({name: e.target.value})
              }}/>
            </div>

            <div className="item">
              <i className="fa fa-key" aria-hidden="true"></i>
              <input allowClear={true} type="password" placeholder="Password" value={this.state.password}
                     onChange={(e) => {
                       this.setState({password: e.target.value})
                     }}/>
            </div>

            <div className="button" onClick={this.login}>
              <button>Login</button>
            </div>
            <div>
              <a href="#/register"><font color="#000000">Create new account</font></a>
            </div>
          </div>
        </div>

    );
  }

  login = (e) => {
    e.preventDefault();
    if(this.state.name != ''){
      let data = new FormData();
      data.append('username',this.state.name);
      data.append('password',this.state.password);
      axios.post('http://localhost:8080/user/signin',data).then(res=>{
        this.setState({
          name:'',
          password:''
        });
        if(res.data.code == 100){
          this.props.history.push({pathname:'/homepage',state:{id:res.data.data.id}});
        }
        else if(res.data.code == 400){
          message.info(res.data.msg);
        }
      })
    }
  }

}

